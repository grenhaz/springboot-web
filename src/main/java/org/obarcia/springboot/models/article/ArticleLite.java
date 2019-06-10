package org.obarcia.springboot.models.article;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;

/**
 * Artículo (Lite).
 * 
 * @author obarcia
 */
@Entity
@Immutable
@Table(name = "article")
public class ArticleLite extends ArticleBase {}