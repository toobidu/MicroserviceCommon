package com.example.commonservice.Model.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "ITEM")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode(of = "itemId")
@ToString(of = {"itemId", "itemCode", "itemName", "itemValue", "parentItemId", "categoryId"})
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_sequence")
    @SequenceGenerator(
            name = "item_sequence",
            sequenceName = "ITEM_SEQ",
            allocationSize = 1
    )
    @Column(name = "ITEM_ID")
    private Long itemId;

    @Version
    @Column(name = "VERSION")
    private Long version;

    @Column(name = "ITEM_NAME", length = 500, nullable = false)
    private String itemName;

    @Column(name = "ITEM_CODE", length = 100, nullable = false)
    private String itemCode;

    @Column(name = "ITEM_VALUE", length = 500, nullable = false)
    private String itemValue;

    @Column(name = "PARENT_ITEM_ID")
    private Long parentItemId;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category categoryId;

    @Column(name = "STATUS")
    private Boolean status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_TIME")
    @Setter(AccessLevel.NONE)
    private Date createdTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_TIME")
    @Setter(AccessLevel.NONE)
    private Date updatedTime;

    @Column(name = "CREATED_USER")
    private Long createdUser;

    @Column(name = "UPDATED_USER")
    private Long updatedUser;

    //Constructor
    public Item(
            Long itemId,
            String itemName,
            String itemCode,
            String itemValue,
            Long parentItemId,
            Category categoryId,
            Boolean status,
            Long createdUser,
            Long updatedUser
    ) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemCode = itemCode;
        this.itemValue = itemValue;
        this.parentItemId = parentItemId;
        this.categoryId = categoryId;
        this.status = status;
        this.createdUser = createdUser;
        this.updatedUser = updatedUser;
        this.createdTime = new Date();  // Set createdTime to current time
        this.updatedTime = new Date();  // Set updatedTime to current time
    }

    @PrePersist
    protected void toCreate() {
        this.createdTime = new Date();
        this.updatedTime = new Date();
    }

    @PreUpdate
    protected void toUpdate() {
        this.updatedTime = new Date();
    }
}
