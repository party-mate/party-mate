package com.example.partymate.model;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.CascadeType.ALL;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author  Unagi_zoso
 * @since  2023-10-17
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Enumerated(STRING)
    private CategoryConstants categoryName;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "post_id")
    private Post post;
}
