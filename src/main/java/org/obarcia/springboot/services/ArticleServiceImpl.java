package org.obarcia.springboot.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.hibernate.HibernateException;
import org.obarcia.springboot.components.datatables.DataTablesColumn;
import org.obarcia.springboot.components.datatables.DataTablesOrder;
import org.obarcia.springboot.components.datatables.DataTablesResponse;
import org.obarcia.springboot.components.datatables.DataTablesRequest;
import org.obarcia.springboot.exceptions.SaveException;
import org.obarcia.springboot.models.ListPagination;
import org.obarcia.springboot.models.entity.article.Article;
import org.obarcia.springboot.models.entity.article.Comment;
import org.obarcia.springboot.repositories.ArticleRepository;
import org.obarcia.springboot.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio de artículos y comentarios.
 * 
 * @author obarcia
 */
@Service
public class ArticleServiceImpl implements ArticleService
{
    /**
     * Instancia del repositorio de artículos.
     */
    @Autowired
    private ArticleRepository articleRepository;
    /**
     * Instancia del repositorio de comentarios.
     */
    @Autowired
    private CommentRepository commentRepository;
    
    @Override
    @Transactional(readOnly = true)
    public DataTablesResponse<Article> getArticles(DataTablesRequest req)
    {
        // Aplicar filtros
        Map<String, Object> filters = new HashMap<>();
        for (Map.Entry<String, DataTablesColumn> entry : req.getColumns().entrySet()) {
            filters.put(entry.getKey(), entry.getValue().getSearch());
        }
        if (req.getSearch() != null && !req.getSearch().isEmpty()) {
            filters.put("all", req.getSearch());
        }
        
        // Orden
        Sort sort = Sort.unsorted();
        for (DataTablesOrder order: req.getOrders()) {
            if (order.getDir() == DataTablesOrder.ORDER_ASC) {
                sort.and(Sort.by(order.getData()).ascending());
            } else {
                sort.and(Sort.by(order.getData()).descending());
            }
        }
        
        // Paginación
        Pageable pagination = PageRequest.of(req.getPage(), req.getLength(), sort);
        
        // Obtener los registros
        List<Article> records = articleRepository.findByFilter(filters, pagination);
        DataTablesResponse<Article> response = new DataTablesResponse<>();
        response.setDraw(req.getDraw());
        response.setData(records);
        response.setRecordsFiltered(articleRepository.countByFilter(filters));
        response.setRecordsTotal(articleRepository.count());
        return response;
    }
    @Override
    @Transactional(readOnly = true)
    public DataTablesResponse<Comment> getComments(Integer id, DataTablesRequest req)
    {
        // Aplicar filtros
        Map<String, Object> filters = new HashMap<>();
        for (Map.Entry<String, DataTablesColumn> entry : req.getColumns().entrySet()) {
            filters.put(entry.getKey(), entry.getValue().getSearch());
        }
        if (req.getSearch() != null && !req.getSearch().isEmpty()) {
            filters.put("all", req.getSearch());
        }
        
        // Orden
        Sort sort = Sort.unsorted();
        for (DataTablesOrder order: req.getOrders()) {
            if (order.getDir() == DataTablesOrder.ORDER_ASC) {
                sort.and(Sort.by(order.getData()).ascending());
            } else {
                sort.and(Sort.by(order.getData()).descending());
            }
        }
        
        // Paginación
        Pageable pagination = PageRequest.of(req.getPage(), req.getLength(), sort);
        
        // Obtener los registros
        List<Comment> records = commentRepository.findByFilter(filters, pagination);
        DataTablesResponse<Comment> response = new DataTablesResponse<>();
        response.setDraw(req.getDraw());
        response.setData(records);
        response.setRecordsFiltered(commentRepository.countByFilter(filters));
        response.setRecordsTotal(commentRepository.count());
        return response;
    }
    @Override
    @Transactional(readOnly = true)
    public ListPagination<Article> getArticlesAll(int page, int perPage)
    {
        List<Article> slist = articleRepository.findAll(PageRequest.of(page - 1, perPage, Sort.by("id")));
        
        return createListPagination(page, perPage, articleRepository.count(), slist);
    }
    @Override
    @Transactional(readOnly = true)
    public ListPagination<Article> getArticlesAll(int page, int perPage, String type)
    {
        Map<String, Object> filters = new HashMap<>();
        filters.put("active", Boolean.TRUE);
        filters.put("type", type);
        
        List<Article> slist = articleRepository.findByFilter(filters, PageRequest.of(page - 1, perPage, Sort.by("publish").descending()));
        
        return createListPagination(page, perPage, articleRepository.countByFilter(filters), slist);
    }
    @Override
    @Transactional(readOnly = true)
    public ListPagination<Article> getArticlesAll(int page, int perPage, String tag, String type)
    {
        Map<String, Object> filters = new HashMap<>();
        filters.put("active", Boolean.TRUE);
        filters.put("tag", tag);
        filters.put("type", type);
        
        List<Article> slist = articleRepository.findByFilter(filters, PageRequest.of(page - 1, perPage, Sort.by("publish").descending()));
        
        return createListPagination(page, perPage, articleRepository.countByFilter(filters), slist);
    }
    @Override
    @Transactional(readOnly = true)
    public ListPagination<Article> getArticlesSearch(int page, int perPage, String tag, String search)
    {
        Map<String, Object> filters = new HashMap<>();
        filters.put("active", Boolean.TRUE);
        filters.put("tag", tag);
        filters.put("all", search);
        
        List<Article> slist = articleRepository.findByFilter(filters, PageRequest.of(page - 1, perPage, Sort.by("publish").descending()));
        
        return createListPagination(page, perPage, articleRepository.countByFilter(filters), slist);
    }
    private ListPagination createListPagination(int page, int perPage, long total, List<Article> slist)
    {
        // Obtener el contador de comentarios
        if (slist != null) {
            for (Article a: slist) {
                a.getCommentsCount();
            }
        }
        
        // Listado paginado
        ListPagination list = new ListPagination();
        list.setRecords(slist);
        list.setOffset((page - 1) * perPage);
        list.setLimit(perPage);
        list.setTotal(total);
        return list;
    }
    @Override
    @Transactional(readOnly = true)
    public List<Article> getArticlesImportants(String tag, int count)
    {
        Map<String, Object> filters = new HashMap<>();
        filters.put("active", Boolean.TRUE);
        filters.put("tag", tag);
        filters.put("important", true);
        
        List<Article> slist = articleRepository.findByFilter(filters, PageRequest.of(0, count, Sort.by("publish").descending()));
        
        // Obtener el contador de comentarios
        if (slist != null) {
            for (Article a: slist) {
                a.getCommentsCount();
            }
        }
        
        return slist;
    }
    @Override
    @Transactional(readOnly = true)
    public List<Article> getArticlesImportants(String tag, String type, int count)
    {
        Map<String, Object> filters = new HashMap<>();
        filters.put("active", Boolean.TRUE);
        filters.put("tag", tag);
        filters.put("type", type);
        filters.put("important", true);
        
        List<Article> slist = articleRepository.findByFilter(filters, PageRequest.of(0, count, Sort.by("publish").descending()));
        
        // Obtener el contador de comentarios
        if (slist != null) {
            for (Article a: slist) {
                a.getCommentsCount();
            }
        }
        
        return slist;
    }
    @Override
    @Transactional(readOnly = true)
    public List<Article> getArticlesByType(String tag, String type, int count)
    {
        Map<String, Object> filters = new HashMap<>();
        filters.put("active", Boolean.TRUE);
        filters.put("tag", tag);
        filters.put("type", type);
        
        List<Article> slist = articleRepository.findByFilter(filters, PageRequest.of(0, count, Sort.by("publish").descending()));
        
        // Obtener el contador de comentarios
        if (slist != null) {
            for (Article a: slist) {
                a.getCommentsCount();
            }
        }
        
        return slist;
    }
    @Override
    @Transactional(readOnly = true)
    public List<Article> getArticlesMoreComments(String tag, int count)
    {
        // BUG: https://hibernate.atlassian.net/browse/HHH-1615
        return null;
    }
    @Override
    @Transactional(readOnly = true)
    public ListPagination<Comment> getComments(int id, int page, int perPage)
    {
        ListPagination list = new ListPagination();
        list.setRecords(commentRepository.findByArticle(id, PageRequest.of(page - 1, perPage, Sort.by("publish").descending())));
        list.setOffset((page - 1) * perPage);
        list.setLimit(perPage);
        list.setTotal(commentRepository.countByArticle(id));
        return list;
    }
    @Override
    @Transactional(readOnly = true)
    public List<Comment> getLastComments(String tag, int count)
    {
        Map<String, Object> filters = new HashMap<>();
        filters.put("active", Boolean.TRUE);
        filters.put("tag", tag);
        
        List<Comment> list = commentRepository.findByFilter(filters, PageRequest.of(0, count, Sort.by("publish").descending()));
        
        return list;
    }
    @Override
    @Transactional(readOnly = true)
    public List<Comment> getLastCommentsByUser(int id, int count)
    {
        return commentRepository.findByUser(id, PageRequest.of(0, count, Sort.by("publish").descending()));
    }
    @Override
    @Transactional(readOnly = true)
    public Article getArticle(int id)
    {
        Optional<Article> article = articleRepository.findById(id);
        if (article.isPresent()) {
            return article.get();
        }
        
        return null;
    }
    @Override
    @Transactional(readOnly = true)
    public Comment getComment(int id)
    {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()) {
            return comment.get();
        }
        
        return null;
    }
    @Override
    @Transactional(readOnly = true)
    public Article getArticleByTitle(String title)
    {
        return articleRepository.findByTitle(title);
    }
    @Override
    @Transactional
    public void save(Article article) throws SaveException
    {
        try {
            articleRepository.save(article);
        } catch (HibernateException e) {
            throw new SaveException();
        }
    }
    @Override
    @Transactional
    public void save(Comment comment) throws SaveException
    {
        try {
            commentRepository.save(comment);
        } catch (HibernateException e) {
            throw new SaveException();
        }
    }
}