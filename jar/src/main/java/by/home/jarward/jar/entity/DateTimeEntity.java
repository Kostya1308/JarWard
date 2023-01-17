package by.home.jarward.jar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@ToString
@MappedSuperclass
public class DateTimeEntity {

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dateTimeCreate;
    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDateTime dateTimeUpdate;


}
