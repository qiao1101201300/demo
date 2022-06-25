package com.qs.demo.docker;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONUtil;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
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
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author:qiaoshen
 * @Email:qiaoshen@iristar.com.cn
 * @Date 2022/6/10
 */
public class DockerUtils {
    private String host;
    private String apiVersion;
    private String username;
    private String password;
    /*测试配置
    private String host = "tcp://192.168.110.21:2375";
    private String apiVersion = "2.0";*/

    public DockerUtils(String host, String apiVersion) {
        this.host = host;
        this.apiVersion = apiVersion;
    }

    /**
     * 创建docker连接
     *
     * @return
     * @throws URISyntaxException
     */
    public DockerClient connect() throws URISyntaxException {
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
     */
    public void dockerPing() {
        DockerClient connect = null;
        try {
            connect = connect();
            connect.pingCmd().exec();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } finally {
            IoUtil.close(connect);
        }
    }

    /**
     * docker信息
     */
    public String dockerInfo() {
        DockerClient connect = null;
        try {
            connect = connect();
            Info info = connect.infoCmd().exec();
            return JSONUtil.toJsonStr(info);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } finally {
            IoUtil.close(connect);
        }
    }

    /**
     * 创建容器
     *
     * @param containerCmd
     * @return
     */
    public CreateContainerResponse createContainer(CreateContainerCmd containerCmd) {
        try {
            return containerCmd.exec();
        } finally {
            containerCmd.close();
        }

    }

    /**
     * 启动容器
     *
     * @param id
     */
    public void startContainer(String id) {
        DockerClient connect = null;
        try {
            connect = connect();
            connect.startContainerCmd(id).exec();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } finally {
            IoUtil.close(connect);
        }
    }

    /**
     * 停止容器
     *
     * @param id
     */
    public void stopContainer(String id) {
        DockerClient connect = null;
        try {
            connect = connect();
            connect.stopContainerCmd(id).exec();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } finally {
            IoUtil.close(connect);
        }
    }

    /**
     * 删除容器
     *
     * @param id
     */
    public void removeContainer(String id) {
        DockerClient connect = null;
        try {
            connect = connect();
            connect.stopContainerCmd(id).exec();
            connect.removeContainerCmd(id).exec();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } finally {
            IoUtil.close(connect);
        }
    }

    /**
     * 构建镜像
     *
     * @param buildImageCmd
     * @return
     */
    public String buildImage(BuildImageCmd buildImageCmd) {
        BuildImageResultCallback callback = new BuildImageResultCallback() {
            @Override
            public void onNext(BuildResponseItem item) {
                System.out.print(item.getStream());
                super.onNext(item);
            }
        };
        try {
            return buildImageCmd.exec(callback).awaitImageId();
        } finally {
            buildImageCmd.close();
        }

    }

    /**
     * 重命名镜像
     *
     * @param imageId    镜像id 或 repository:tag
     * @param repository 新repository
     * @param version    新tag
     */
    public void tagImage(String imageId, String repository, String version) {
        DockerClient connect = null;
        try {
            connect = connect();
            connect.tagImageCmd(imageId, repository, version).exec();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } finally {
            IoUtil.close(connect);
        }
    }

    /**
     * 推送镜像
     *
     * @param pushImageCmd 推送镜像命令
     * @throws InterruptedException
     */
    public void pushImage(PushImageCmd pushImageCmd) {
        try {
            pushImageCmd.start().awaitCompletion(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            pushImageCmd.close();
        }
    }

    /**
     * 删除镜像
     *
     * @param imageId 镜像id 或 repository:tag
     */
    public void removeImage(String imageId) {
        DockerClient connect = null;
        try {
            connect = connect();
            connect().removeImageCmd(imageId).exec();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } finally {
            IoUtil.close(connect);
        }
    }

    /**
     * 拉取镜像
     *
     * @param imageId
     */
    public void pullImage(String imageId) {
        DockerClient connect = null;
        try {
            connect = connect();
            connect.pullImageCmd(imageId)
                    .start()
                    .awaitCompletion(30, TimeUnit.SECONDS);
        } catch (URISyntaxException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            IoUtil.close(connect);
        }
    }

    /**
     * 远程镜像信息
     *
     * @param imageId 镜像id 或 repository:tag
     * @return
     */
    public InspectImageResponse inspectImage(String imageId) {
        DockerClient connect = null;
        try {
            connect = connect();
            return connect.inspectImageCmd(imageId).exec();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } finally {
            IoUtil.close(connect);
        }
    }

    /**
     * 远程docker所有镜像
     *
     * @return
     */
    public List<Image> dockerImages() {
        DockerClient connect = null;
        try {
            connect = connect();
            return connect.listImagesCmd().exec();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } finally {
            IoUtil.close(connect);
        }
    }

    /**
     * docker 上传加载镜像
     *
     * @param file docker save 命令导出镜像文件
     */
    public void dockerLoad(File file) {
        if (!file.exists()) {
            return;
        }
        DockerClient connect = null;
        try {
            connect = connect();
            connect.loadImageCmd(FileUtil.getInputStream(file)).exec();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } finally {
            IoUtil.close(connect);
        }
    }

    /**
     * docker所有容器信息
     *
     * @return
     * @throws URISyntaxException
     */
    public List<Container> dockerContainers() {
        DockerClient connect = null;
        try {
            connect = connect();
            return connect.listContainersCmd().withShowAll(true).exec();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } finally {
            IoUtil.close(connect);
        }
    }

    public void logContainer(String containerId, ResultCallback.Adapter<Frame> adapter) {
        DockerClient connect = null;
        try {
            connect = connect();
            connect.logContainerCmd(containerId).withFollowStream(true).withTimestamps(true).withStdOut(true).withStdErr(true).withTailAll()
                    .exec(adapter).awaitCompletion();
        } catch (URISyntaxException | InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                adapter.close();
                if (connect != null) {
                    connect.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        DockerUtils dockerUtils = new DockerUtils("tcp://192.168.110.21:2375", "v2.0");
        dockerUtils.dockerImages().forEach(image -> {
            System.out.println(JSONUtil.toJsonStr(image));
        });
        /*DockerUtils dockerUtils = new DockerUtils("tcp://192.168.110.21:2375", "v2.0");
        //构建本地镜像到远程
        String imageId = "cc0789b9b085";
        //本地构建位置
        String filePath = "D:\\java2\\irs-knowledge-base\\irs-knowledge-base";
        DockerClient connect = null;
        try {
            connect = dockerUtils.connect();
            imageId = dockerUtils.buildImage(connect.buildImageCmd(new File(filePath))
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
        //构建容器
        ExposedPort exposedPort1 = new ExposedPort(8012);
        ExposedPort exposedPort2 = new ExposedPort(8011);
        CreateContainerCmd createContainerCmd = dockerUtils.connect().createContainerCmd(imageId)
                .withEnv("SPRING_CLOUD_EUREKA_ZONE=http://192.168.188.5:8080/eureka/",
                        "SERVER_PORT=8012",
                        "MYSQL_URL=jdbc:mysql://192.168.110.21:3306/IRS_KNOWLEDGE_BASE?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false&allowMultiQueries=true&zeroDateTimeBehavior=CONVERT_TO_NULL",
                        "MYSQL_USER=admin",
                        "MYSQL_PASSWORD=admin",
                        "REDIS_HOST=192.168.110.21",
                        "REDIS_PORT=6379",
                        "SERVER_IP=192.168.110.21")
                .withExposedPorts(exposedPort1, exposedPort2)
//                .withHostConfig(new HostConfig().withPortBindings(new PortBinding(Ports.Binding.bindPort(8012), exposedPort1), new PortBinding(Ports.Binding.bindPort(8011), exposedPort2)));
        CreateContainerResponse container = dockerUtils.createContainer(createContainerCmd);
        //启动容器
        dockerUtils.startContainer(container.getId());
        //上传本地镜像到远程加载
        dockerUtils.dockerLoad(new File("D:\\IdeaProjects\\irs-knowledge-base\\kb-register.tar"));
        InspectImageResponse inspectImageResponse = dockerUtils.inspectImage("irs-knowledge-base/kb-register/linux64:v1.0.0");*/
//        dockerUtils.logContainer("1e88b1f97861");
    }

}
