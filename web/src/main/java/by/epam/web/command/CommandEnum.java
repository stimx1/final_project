package by.epam.web.command;

import by.epam.web.command.admin.*;
import by.epam.web.command.user.*;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    REGISTRATION {
        {
            this.command = new RegistrationCommand();
        }
    },
    CHANGE_LOCALE {
        {
            this.command = new ChangeLocaleCommand();
        }
    },
    GET_USERS {
        {
            this.command = new GetUsersCommand();
        }
    },
    DELETE_USER {
        {
            this.command = new DeleteUserCommand();
        }
    },
    GET_INSTRUCTORS {
        {
            this.command = new GetInstructorsCommand();
        }
    },
    ADD_INSTRUCTOR {
        {
            this.command = new AddInstructorCommand();
        }
    },
    DELETE_INSTRUCTOR {
        {
            this.command = new DeleteInstructorCommand();
        }
    },
    GET_ASSIGNMENT {
        {
            this.command = new GetAssignmentCommand();
        }
    },
    ADD_DIET {
        {
            this.command = new AddDietCommand();
        }
    },
    DELETE_DIET {
        {
            this.command = new DeleteDietCommand();
        }
    },
    ADD_EXERCISE {
        {
            this.command = new AddExerciseCommand();
        }
    },
    DELETE_EXERCISE {
        {
            this.command = new DeleteExerciseCommand();
        }
    },
    ASSIGN_DIET {
        {
            this.command = new AssignDietCommand();
        }
    },
    DELETE_ASSIGNED_DIET {
        {
            this.command = new DeleteAssignedDietCommand();
        }
    },
    ASSIGN_EXERCISE {
        {
            this.command = new AssignExerciseCommand();
        }
    },
    DELETE_ASSIGNED_EXERCISE {
        {
            this.command = new DeleteAssignedExerciseCommand();
        }
    },
    CHANGE_INSTRUCTOR {
        {
            this.command = new ChangeInstructorCommand();
        }
    },
    GET_SERVICES {
        {
            this.command = new GetServicesCommand();
        }
    },
    ADD_SUBSCRIPTION {
        {
            this.command = new AddSubscriptionCommand();
        }
    },
    DELETE_SUBSCRIPTION {
        {
            this.command = new DeleteSubscriptionCommand();
        }
    },
    USER_ACCOUNT {
        {
            this.command = new UserAccountCommand();
        }
    },
    DEPOSIT_ACCOUNT {
        {
            this.command = new DepositAccountCommand();
        }
    },
    BUY_SUBSCRIPTION {
        {
            this.command = new BuySubscriptionCommand();
        }
    },
    GET_DIETS {
        {
            this.command = new GetDietsCommand();
        }
    },
    GET_EXERCISES {
        {
            this.command = new GetExercisesCommand();
        }
    },
    GET_ASSIGNED_DIET {
        {
            this.command = new GetAssignedDietCommand();
        }
    },
    GET_ASSIGNED_EXERCISE {
        {
            this.command = new GetAssignedExerciseCommand();
        }
    },
    COACHING_ROOM {
        {
            this.command = new CoachingRoomCommand();
        }
    },
    UPDATE_USER {
        {
            this.command = new UpdateUserCommand();
        }
    };
    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
