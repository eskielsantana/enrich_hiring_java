package com.domain.request;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class RequestService {
    private static final Logger LOGGER = Logger.getLogger(RequestService.class);

    public void postFile(String url, File file) {
        HttpPost post = new HttpPost(url);

        HttpEntity entity = new FileEntity(file, ContentType.create("text/csv"));

        post.setEntity(entity);

        try(CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
        } catch (IOException e) {
            LOGGER.error("IOException while sending the dailyVehiclesReport: " + e.getCause());
        }
    }
}
