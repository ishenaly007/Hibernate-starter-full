package com.abit.hibernate.starter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "trainer_course")
public class TrainerCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public void addTrainer(Trainer trainer) {
        this.trainer = trainer;
        trainer.getTrainerCourses().add(this);
    }

    public void addCourse(Course course) {
        this.course = course;
        course.getTrainerCourses().add(this);
    }
}