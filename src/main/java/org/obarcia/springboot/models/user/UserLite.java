package org.obarcia.springboot.models.user;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;

/**
 * User (Lite).
 * 
 * @author obarcia
 */
@Entity
@Immutable
@Table(name = "usuario")
public class UserLite extends UserBase
{
}
