package kube.board.article.controller;

import kube.board.article.service.ArticleService;
import kube.board.article.service.request.ArticleCreateRequest;
import kube.board.article.service.request.ArticleUpdateRequest;
import kube.board.article.service.response.ArticleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ArticleController {

  private final ArticleService articleService;

  @GetMapping("/v1/articles/{articleId}")
  public ArticleResponse read(@PathVariable Long articleId) {
    return articleService.read(articleId);
  }

  @PostMapping("/v1/articles")
  public ArticleResponse create(@RequestBody ArticleCreateRequest request) {
    return articleService.create(request);
  }

  @PutMapping("/v1/articles/{articleId}")
  public ArticleResponse create(
      @PathVariable Long articleId,
      @RequestBody ArticleUpdateRequest request) {
    return articleService.update(articleId, request);
  }

  @DeleteMapping("/v1/articles/{articleId}")
  public void delete(@PathVariable Long articleId) {
    articleService.delete(articleId);
  }

}
