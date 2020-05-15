package by.itstep.lms.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.Lob;

import java.util.Base64;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private String name;

    private String path;
}
