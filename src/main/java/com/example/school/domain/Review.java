package com.example.school.domain;

import com.example.school.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facility_id")
    private Facility facility;


    private String title;

    private Float score;

    private String body;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<ReviewImage> reviewImages;


    public void setMember(Member member){
        if(this.member != null)
            member.getReviewList().remove(this);
        this.member = member;
        member.getReviewList().add(this);
    }

    public void setFacility(Facility facility){
        if (this.facility != null)
            facility.getReviewList().remove(this);
        this.facility = facility;
        facility.getReviewList().add(this);
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setScore(Float score) {
        this.score = score;
    }
    public void setBody(String body) {
        this.body = body;
    }

}
