package com.abit.hibernate.starter.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@ToString(exclude = {"course", "profile"})
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    @ManyToOne()
    @JoinColumn(name = "course_id", nullable = true)
    private Course course;

    public void setCourse(Course course) {
        this.course = course;
        course.getStudents().add(this);
    }

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private StudentProfile profile;
}