package com.task.infra.service;

import com.task.infra.properties.WikiProperties;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static com.task.test.util.TestUtil.HTML_STRING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WikipediaInputDataServiceTest {

    @Mock
    private WikiProperties properties;

    @InjectMocks
    WikipediaInputDataService victim;

    @Test
    void fetchAll() throws IOException {

        try (MockedStatic<HttpConnection> httpConnection = mockStatic(HttpConnection.class)) {

            var connection = mock(Connection.class);
            var document = mock(Document.class);
            Element element = Jsoup.parse(HTML_STRING, "", Parser.xmlParser());

            when(properties.getUrl()).thenReturn("url");
            httpConnection.when(() -> HttpConnection.connect("url")).thenReturn(connection);
            when(connection.get()).thenReturn(document);
            when(document.body()).thenReturn(element);

            var result = victim.fetchAll();

            assertEquals(9, result.size());
        }
    }


}
