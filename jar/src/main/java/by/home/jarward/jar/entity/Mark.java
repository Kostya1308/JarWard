package by.home.jarward.jar.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mark mark)) return false;
        return markId.equals(mark.markId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(markId);
    }
}
