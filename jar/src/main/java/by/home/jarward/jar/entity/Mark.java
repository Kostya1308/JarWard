package by.home.jarward.jar.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

@Entity
@Getter
@Setter
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class Mark {
    @Version
    private static long serialVersionUID;
    @EmbeddedId
    private MarkId markId;

    @Column
    private int mark;
}
