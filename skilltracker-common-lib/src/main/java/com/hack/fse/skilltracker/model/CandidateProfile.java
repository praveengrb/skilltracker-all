package com.hack.fse.skilltracker.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.stereotype.Component;

import javax.persistence.Id;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ToString
@DynamoDBTable(tableName = "skilltrackerdb")
public class CandidateProfile implements Serializable {

    @Setter
    List<Skill> technicalSkills;

    @Setter
    List<Skill> nonTechnicalSkills;



    @Id

    @Setter
    private String id;

    @Getter
    @Setter
    @DynamoDBAttribute
    private String userId;

    @Getter
    @Setter
    @DynamoDBAttribute
    @NotBlank(message = "{name.notempty}")
    @NotEmpty(message = "{name.notempty}")
    @Size(min = 5, max = 30, message = "{name.lengthmismatch}")
    private String name;

    @Getter
    @Setter
    @DynamoDBAttribute
    @NotBlank(message = "{associateId.notempty}")
    @NotEmpty(message = "{associateId.notempty}")
    @Size(min = 5, max = 30, message = "{associateId.lengthmismatch}")
    //@Pattern(regexp = "^CTS",message="{associateID.prefix}")
    @Indexed(unique = true)
    private String associateId;

    @Getter
    @Setter
    @DynamoDBAttribute
    @NotBlank(message = "{mobile.notempty}")
    @Size(min = 10, max = 10, message = "{mobile.lengthmismatch}")
    @Pattern(regexp = "^(0|[1-9][0-9]*)$", message = "{mobile.numericexception}")
    @Indexed(unique = true)
    private String mobile;

    @Getter
    @Setter
    @DynamoDBAttribute
    @Email(message = "emailID.notvalid")
    @Indexed(unique = true)
    private String email;

    @CreatedDate
    @Getter
    @Setter
    @DynamoDBAttribute
    private LocalDateTime created;

    @LastModifiedDate
    @Getter
    @Setter
    @DynamoDBAttribute
    private LocalDateTime modified;

    public CandidateProfile with(CandidateProfile that) {
        this.setEmail(that.getEmail());
        this.setUserId(that.getUserId());
        this.setAssociateId(that.getAssociateId());
        this.setMobile(that.getMobile());
        this.setName(that.getName());
        this.setNonTechnicalSkills(that.getNonTechnicalSkills());
        this.setTechnicalSkills(that.getTechnicalSkills());
        this.setCreated(that.getCreated());
        this.setModified(that.getModified());
        return this;
    }
    public CandidateProfile subpress(CandidateProfile that) {
        this.setEmail(that.getEmail());
        this.setUserId(that.getUserId());
        this.setAssociateId(that.getAssociateId());
        this.setMobile(that.getMobile());
        this.setName(that.getName());
        //this.setNonTechnicalSkills(that.getNonTechnicalSkills());
        //this.setTechnicalSkills(that.getTechnicalSkills());
        //this.setCreated(that.getCreated());
        //this.setModified(that.getModified());
        return this;
    }
    @DynamoDBTypeConvertedEnum
    @DynamoDBTypeConverted(converter = SkillsConverter.class)
    @DynamoDBAttribute(attributeName = "technicalskills")
    public List<Skill> getTechnicalSkills(){
        return this.technicalSkills;
    }
    @DynamoDBTypeConvertedEnum
    @DynamoDBTypeConverted(converter = SkillsConverter.class)
    @DynamoDBAttribute(attributeName = "nontechnicalskills")
    public List<Skill> getNonTechnicalSkills(){
        return this.nonTechnicalSkills;
    }
    @DynamoDBHashKey(attributeName = "id")
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return id;
    }
}
