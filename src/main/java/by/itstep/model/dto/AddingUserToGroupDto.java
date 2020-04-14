package by.itstep.model.dto;

import by.itstep.model.Group;
import by.itstep.model.User;
import lombok.Data;

@Data
public class AddingUserToGroupDto {
    private User user;
    private Group group;
}