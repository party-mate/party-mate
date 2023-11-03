package com.example.partymate.domain.category;

import com.example.partymate.domain.party.Party;
import com.example.partymate.domain.util.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author : Unagi_zoso
 * @date : 2023-10-17
 */
@Entity
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(length = 72, nullable = false)
    private String categoryName;

    @OneToOne(mappedBy = "category")
    private Party party;
}
