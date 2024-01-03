package domain.upload;

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
public class UploadService {
    private static final Logger LOGGER = Logger.getLogger(UploadService.class);
    private static final String EXTERNAL_SERVICE_URL = "https://enricheskiel.free.beeceptor.com";

    public void uploadReport(File report) {
        HttpPost post = new HttpPost(EXTERNAL_SERVICE_URL);

        HttpEntity entity = new FileEntity(report, ContentType.create("text/csv"));

        post.setEntity(entity);

        try(CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse ignored = httpClient.execute(post)) {
        } catch (IOException e) {
            LOGGER.error("IOException while sending the dailyVehiclesReport: " + e.getCause());
        }
    }


}
