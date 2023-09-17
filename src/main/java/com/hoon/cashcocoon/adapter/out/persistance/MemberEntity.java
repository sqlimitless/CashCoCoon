package com.hoon.cashcocoon.adapter.out.persistance;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "member")
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

}
