package com.qs.demo.thread;

import cn.hutool.json.JSONUtil;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.*;
import com.github.dockerjava.api.model.*;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author:qiaoshen
 * @Email:qiaoshen@iristar.com.cn
 * @Date 2022/6/10
 */
public class DockerUtils {
    private static String host = "tcp://192.168.110.21:2375";
    private static String apiVersion = "2.0";

    /**
     * 创建docker连接
     *
     * @return
     * @throws URISyntaxException
     */
    public static DockerClient connect() throws URISyntaxException {
        //创建DefaultDockerClientConfig
        DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withApiVersion(apiVersion)
                .withDockerHost(host)
                .build();
        //创建DockerHttpClient
        DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(config.getDockerHost())
                .maxConnections(100)
                .connectionTimeout(Duration.ofSeconds(30))
                .responseTimeout(Duration.ofSeconds(45))
                .build();
        //创建DockerClient
        return DockerClientImpl.getInstance(config, httpClient);
    }

    /**
     * 测试docker连接
     *
     * @throws URISyntaxException
     */
    public static void dockerPing() throws URISyntaxException {
        DockerClient client = connect();
        client.pingCmd().exec();
    }

    /**
     * docker信息
     *
     * @throws URISyntaxException
     */
    public static void dockerInfo() throws URISyntaxException {
        DockerClient client = connect();
        Info info = client.infoCmd().exec();
        System.out.println("docker info =" + JSONUtil.toJsonStr(info));
    }

    /**
     * 创建容器
     *
     * @param containerCmd
     * @return
     */
    public static CreateContainerResponse createContainer(CreateContainerCmd containerCmd) {
        return containerCmd.exec();
    }

    /**
     * 启动容器
     *
     * @param id
     * @throws URISyntaxException
     */
    public static void startContainer(String id) throws URISyntaxException {
        connect().startContainerCmd(id).exec();
    }

    /**
     * 停止容器
     *
     * @param id
     * @throws URISyntaxException
     */
    public static void stopContainer(String id) throws URISyntaxException {
        connect().stopContainerCmd(id).exec();
    }

    /**
     * 构建镜像
     *
     * @param buildImageCmd
     * @return
     */
    public static String buildImage(BuildImageCmd buildImageCmd) {
        BuildImageResultCallback callback = new BuildImageResultCallback() {
            @Override
            public void onNext(BuildResponseItem item) {
                System.out.print(item.getStream());
                super.onNext(item);
            }
        };
        return buildImageCmd.exec(callback).awaitImageId();
    }

    /**
     * 重命名镜像
     *
     * @param imageId
     * @param repository
     * @param version
     * @throws URISyntaxException
     */
    public static void tagImage(String imageId, String repository, String version) throws URISyntaxException {
        connect().tagImageCmd(imageId, repository, version).exec();
    }

    /**
     * 推送镜像
     *
     * @param pushImageCmd
     * @throws InterruptedException
     */
    public static void pushImage(PushImageCmd pushImageCmd) throws InterruptedException {
        pushImageCmd.start().awaitCompletion(30, TimeUnit.SECONDS);
    }

    /**
     * 删除镜像
     *
     * @param imageId
     * @throws URISyntaxException
     */
    public static void removeImage(String imageId) throws URISyntaxException {
        connect().removeImageCmd(imageId).exec();
    }

    /**
     * 拉取镜像
     *
     * @param imageId
     * @throws URISyntaxException
     * @throws InterruptedException
     */
    public static void pullImage(String imageId) throws URISyntaxException, InterruptedException {
        connect().pullImageCmd(imageId)
                .start()
                .awaitCompletion(30, TimeUnit.SECONDS);
    }

    /**
     * 远程镜像信息
     *
     * @throws URISyntaxException
     * @throws InterruptedException
     */
    public static InspectImageResponse inspectImage(String imageId) throws URISyntaxException, InterruptedException {
        return connect().inspectImageCmd(imageId).exec();
    }

    /**
     * docker所有镜像
     *
     * @return
     * @throws URISyntaxException
     */
    public static List<Image> dockerImages() throws URISyntaxException {
        return connect().listImagesCmd().exec();
    }

    public static void main(String[] args) throws URISyntaxException {
        String imageId = "276dca6e11e8";
        DockerClient connect = null;
        try {
            connect = connect();
            imageId = buildImage(connect.buildImageCmd(new File("D:\\java2\\irs-knowledge-base\\irs-knowledge-base"))
                    .withBuildArg("service_dir", "irs-auth-center")
                    .withTags(new HashSet<>(Collections.singletonList("irs-knowledge-base/irs-auth-center/linux64:v1.0.0"))));
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                connect.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        CreateContainerResponse container = createContainer(connect().createContainerCmd(imageId)
                .withHostConfig(new HostConfig().withPortBindings(new PortBinding(Ports.Binding.bindPort(8010), new ExposedPort(8010)), new PortBinding(Ports.Binding.bindPort(8011), new ExposedPort(8011))))
                .withEnv("SPRING_CLOUD_EUREKA_ZONE=http://192.168.110.108:8080/eureka/",
                        "SERVER_PORT=8010",
                        "MYSQL_URL=jdbc:mysql://192.168.110.21:3306/IRS_KNOWLEDGE_BASE?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false&allowMultiQueries=true&zeroDateTimeBehavior=CONVERT_TO_NULL",
                        "MYSQL_USER=admin",
                        "MYSQL_PASSWORD=admin",
                        "REDIS_HOST=192.168.110.21",
                        "REDIS_PORT=6379"));
        startContainer(container.getId());
//        removeImage("3210f0c4aaf8");
    }

}
