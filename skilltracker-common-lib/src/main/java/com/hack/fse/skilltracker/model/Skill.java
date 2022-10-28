package com.hack.fse.skilltracker.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 *  Skill has TECH and NON-TECH skills name and its expertise
 */
@Component
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBDocument
public class Skill implements Serializable {
    @Getter @Setter
    private String skillName;
    @Min(0) @Max(20)
    @Getter @Setter
    private int expertise;
}
