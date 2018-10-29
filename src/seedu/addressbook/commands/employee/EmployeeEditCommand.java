package seedu.addressbook.commands.employee;

import seedu.addressbook.commands.Command;
import seedu.addressbook.commands.CommandResult;
import seedu.addressbook.common.Messages;
import seedu.addressbook.data.employee.EditEmployeeDescriptor;
import seedu.addressbook.data.employee.Employee;
import seedu.addressbook.data.employee.EmployeeAddress;
import seedu.addressbook.data.employee.EmployeeEmail;
import seedu.addressbook.data.employee.EmployeeName;
import seedu.addressbook.data.employee.EmployeePhone;
import seedu.addressbook.data.employee.EmployeePosition;
import seedu.addressbook.data.employee.ReadOnlyEmployee;
import seedu.addressbook.data.employee.UniqueEmployeeList.EmployeeNotFoundException;
import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Edits the details of an existing employee in the Rms.
 */
public class EmployeeEditCommand extends Command {
    public static final String COMMAND_WORD = "editemp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the employee identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[p/PHONE] "
            + "[e/EMAIL] "
            + "[a/ADDRESS] "
            + "[pos/POSITION]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "p/97654321 ";

    public static final String MESSAGE_EDIT_EMPLOYEE_SUCCESS = "Edited Person: %1$s";
    // implement check for no field entered
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String EMPTY_EMPLOYEE_PHONE = "00000000";
    public static final String EMPTY_EMPLOYEE_EMAIL = "noargs@noargs.com";
    public static final String EMPTY_EMPLOYEE_ADDRESS = "noargs";
    public static final String EMPTY_EMPLOYEE_POSITION = "noargs";

    private final EditEmployeeDescriptor editEmployeeDescriptor;

    /**
     * @param targetVisibleIndex of the person in the filtered person list to edit
     * @value editEmployeeDescriptor details to edit the employee with
     */
    public EmployeeEditCommand(int targetVisibleIndex,
                               String phone,
                               String email,
                               String address,
                               String position) throws IllegalValueException {
        super(targetVisibleIndex);
        this.editEmployeeDescriptor = new EditEmployeeDescriptor(phone, email, address, position);
    }

    // check for any non null parameters should already be done, probably in parser
    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyEmployee employeeToEdit = getTargetEmployee();
            Employee editedEmployee = createEditedEmployee(employeeToEdit, editEmployeeDescriptor);

            rms.editEmployee(employeeToEdit, editedEmployee);

            return new CommandResult(String.format(MESSAGE_EDIT_EMPLOYEE_SUCCESS, editedEmployee));

        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_INDEX);
        } catch (EmployeeNotFoundException enfe) {
            return new CommandResult(Messages.MESSAGE_EMPLOYEE_NOT_IN_RMS);
        }

    }

    /**
     * Creates and returns an {@code Employee} with the details of {@code employeeToEdit}
     * edited with {@code editEmployeeDescriptor}.
     */
    private static Employee createEditedEmployee(ReadOnlyEmployee employeeToEdit,
                                                 EditEmployeeDescriptor editEmployeeDescriptor) {

        EmployeeName updatedName = employeeToEdit.getName();
        EmployeePhone updatedPhone = checkPhone(editEmployeeDescriptor.getPhone(), employeeToEdit.getPhone());
        EmployeeEmail updatedEmail = checkEmail(editEmployeeDescriptor.getEmail(), employeeToEdit.getEmail());
        EmployeeAddress updatedAddress = checkAddress(editEmployeeDescriptor.getAddress(), employeeToEdit.getAddress());
        EmployeePosition updatedPosition = checkPosition(editEmployeeDescriptor.getPosition(),
                employeeToEdit.getPosition());

        return new Employee(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedPosition);
    }

    /**
    * Check for new phone value.
    */
    private static EmployeePhone checkPhone(EmployeePhone newEdit, EmployeePhone oldInfo) {
        if (newEdit.value == EMPTY_EMPLOYEE_PHONE) {
            return oldInfo;
        }
        return newEdit;
    }

    /**
     * Check for new email value.
     */
    private static EmployeeEmail checkEmail(EmployeeEmail newEdit, EmployeeEmail oldInfo) {
        if (newEdit.value == EMPTY_EMPLOYEE_EMAIL) {
            return oldInfo;
        }
        return newEdit;
    }

    /**
     * Check for new address value.
     */
    private static EmployeeAddress checkAddress(EmployeeAddress newEdit, EmployeeAddress oldInfo) {
        if (newEdit.value == EMPTY_EMPLOYEE_ADDRESS) {
            return oldInfo;
        }
        return newEdit;
    }

    /**
     * Check for new position value.
     */
    private static EmployeePosition checkPosition(EmployeePosition newEdit, EmployeePosition oldInfo) {
        if (newEdit.value == EMPTY_EMPLOYEE_POSITION) {
            return oldInfo;
        }
        return newEdit;
    }
}
