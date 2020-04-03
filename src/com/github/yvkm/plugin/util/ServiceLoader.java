package com.github.yvkm.plugin.util;

import com.github.yvkm.plugin.Translator;

import javax.management.ServiceNotFoundException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Xie Jian Xun
 */
public class ServiceLoader<S> {

    private static final String path = "META-INF/services/";
    private List<S> serviceList;

    public ServiceLoader(Class<S> service) throws ServiceNotFoundException {
        try {
            serviceList = find(service);
        } catch (IOException | InstantiationException | ClassNotFoundException | IllegalAccessException e) {
            throw new ServiceNotFoundException(e.getMessage());
        }

        if (serviceList.size() <= 0) {
            throw new ServiceNotFoundException("无法找到服务:请检查类路径下是否存在META-INF" +
                "/services及相应的类实现配置");
        }
    }

    private List<S> find(Class<S> service) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String name = service.getName();
        InputStream resourceAsStream =
            ServiceLoader.class.getClassLoader().getResourceAsStream(path + name);
        InputStreamReader isr = new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr);

        String className = null;
        ArrayList<S> result = new ArrayList<>();

        while ((className = reader.readLine()) != null) {
            className = className.trim();
            S serviceImpl = (S) Class.forName(className).newInstance();
            result.add(serviceImpl);
        }
        return result;
    }

    public static <S> List<S> load(Class<S> service) throws ServiceNotFoundException {
        return new ServiceLoader<>(service).serviceList;
    }

    public static void main(String[] args) throws ServiceNotFoundException {
        List<Translator> load = ServiceLoader.load(Translator.class);
        load.forEach(System.out::println);
    }
}
