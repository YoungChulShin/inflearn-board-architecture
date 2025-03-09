package kube.board.article.api;

import kube.board.article.service.response.ArticleResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClient.ResponseSpec;

public class ArticleApiTest {

  RestClient restClient = RestClient.create("http://localhost:9000");

  @Test
  void createTest() {
    ArticleResponse response = create(new ArticleCreateRequest("hi", "my content", 1L, 1L));

    System.out.println("response = " + response);
  }

  private ArticleResponse create(ArticleCreateRequest request) {
    return restClient.post()
        .uri("/v1/articles")
        .body(request)
        .retrieve()
        .body(ArticleResponse.class);
  }

  @Test
  void readTest() {
    ArticleResponse response = read(157119720951730176L);
    System.out.println("response = " + response);
  }

  private ArticleResponse read(Long articleId) {
    return restClient.get()
        .uri("/v1/articles/{articleId}", articleId)
        .retrieve()
        .body(ArticleResponse.class);
  }

  @Test
  void updateTest() {
    update(157119720951730176L);
    ArticleResponse response = read(157119720951730176L);
    System.out.println("response = " + response);
  }

  private void update(Long articleId) {
    restClient.put()
        .uri("/v1/articles/{articleId}", articleId)
        .body(new ArticleUpdateRequest("hi 3", "my content 3"))
        .retrieve()
        .body(ArticleResponse.class);
  }

  @Test
  void deleteTest() {
    restClient.delete()
        .uri("/v1/articles/{articleId}", 157119720951730176L);
  }

  @Getter
  @AllArgsConstructor
  static public class ArticleCreateRequest {

    private String title;
    private String content;
    private Long writerId;
    private Long boardId;
  }

  @Getter
  @AllArgsConstructor
  static class ArticleUpdateRequest {

    private String title;
    private String content;
  }

}
