package seedu.addressbook.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

import seedu.addressbook.data.employee.Employee;
import seedu.addressbook.data.employee.EmployeeAddress;
import seedu.addressbook.data.employee.EmployeeEmail;
import seedu.addressbook.data.employee.EmployeeName;
import seedu.addressbook.data.employee.EmployeePhone;
import seedu.addressbook.data.employee.EmployeePosition;
import seedu.addressbook.data.member.Member;
import seedu.addressbook.data.member.MemberName;
import seedu.addressbook.data.menu.Menu;
import seedu.addressbook.data.menu.MenuName;
import seedu.addressbook.data.menu.Price;
import seedu.addressbook.data.menu.Type;
import seedu.addressbook.data.Rms;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.tag.Tag;

/**
 * A utility class to generate test data.
 */
class TestDataHelper{

    Person adam() throws Exception {
        Name name = new Name("Adam Brown");
        Phone privatePhone = new Phone("111111", true);
        Email email = new Email("adam@gmail.com", false);
        Address privateAddress = new Address("111, alpha street", true);
        Tag tag1 = new Tag("tag1");
        Tag tag2 = new Tag("tag2");
        Set<Tag> tags = new HashSet<>(Arrays.asList(tag1, tag2));
        return new Person(name, privatePhone, email, privateAddress, tags);
    }

    Employee peter() throws Exception {
        EmployeeName name = new EmployeeName("Peter Lee");
        EmployeePhone phone = new EmployeePhone("91234567");
        EmployeeEmail email = new EmployeeEmail("PeterLee89@rms.com");
        EmployeeAddress address = new EmployeeAddress("Clementi Ave 2, Blk 543 #13-12");
        EmployeePosition position = new EmployeePosition("Cashier");
        return new Employee(name, phone, email, address, position);
    }

    Member eve() throws Exception {
        MemberName name = new MemberName("Eve");
        return new Member(name);
    }


    Menu burger() throws Exception {
        MenuName name = new MenuName("Cheese Burger");
        Price price = new Price("$5.00");
        Type type = new Type("main");
        Tag tag1 = new Tag("tag1");
        Tag tag2 = new Tag("tag2");
        Set<Tag> tags = new HashSet<>(Arrays.asList(tag1, tag2));
        return new Menu(name, price, type, tags);
    }

    /**
     * Generates a valid person using the given seed.
     * Running this function with the same parameter values guarantees the returned person will have the same state.
     * Each unique seed will generate a unique Person object.
     *
     * @param seed used to generate the person data field values
     * @param isAllFieldsPrivate determines if private-able fields (phone, email, address) will be private
     */
    Person generatePerson(int seed, boolean isAllFieldsPrivate) throws Exception {
        return new Person(
                new Name("Person " + seed),
                new Phone("" + Math.abs(seed), isAllFieldsPrivate),
                new Email(seed + "@email", isAllFieldsPrivate),
                new Address("House of " + seed, isAllFieldsPrivate),
                new HashSet<>(Arrays.asList(new Tag("tag" + Math.abs(seed)), new Tag("tag" + Math.abs(seed + 1))))
        );
    }

    /**
     * Generates a valid employee using the given seed.
     * Running this function with the same parameter values guarantees the returned employee will have the same state.
     * Each unique seed will generate a unique Employee object.
     *
     * @param seed used to generate the employee data field values
     */
    Employee generateEmployee(int seed) throws Exception {
        return new Employee(
                new EmployeeName("Employee " + seed),
                new EmployeePhone("" + Math.abs(seed)),
                new EmployeeEmail(seed + "@email"),
                new EmployeeAddress("House of " + seed),
                new EmployeePosition("Position "+ seed)
        );
    }

    /**
     * Generates a valid member using the given seed.
     * Running this function with the same parameter values guarantees the returned employee will have the same state.
     * Each unique seed will generate a unique Employee object.
     *
     * @param seed used to generate the employee data field values
     */
    Member generateMember(int seed) throws Exception {
        return new Member(
                new MemberName("Member " + seed)
        );
    }
    /**
     * Generates a valid menu item using the given seed.
     * Running this function with the same parameter values guarantees the returned menu item will have the same state.
     * Each unique seed will generate a unique Person object.
     *
     * @param seed used to generate the menu item data field values
     */
    Menu generateMenuItem(int seed) throws Exception {
        return new Menu(
                new MenuName("Person " + seed),
                new Price("" + Math.abs(seed)),
                new Type(("Type " + seed)),
                new HashSet<>(Arrays.asList(new Tag("tag" + Math.abs(seed)), new Tag("tag" + Math.abs(seed + 1))))
        );
    }

    /** Generates the correct add command based on the person given */
    String generateAddCommand(Person p) {
        StringJoiner cmd = new StringJoiner(" ");

        cmd.add("add");

        cmd.add(p.getName().toString());
        cmd.add((p.getPhone().isPrivate() ? "pp/" : "p/") + p.getPhone());
        cmd.add((p.getEmail().isPrivate() ? "pe/" : "e/") + p.getEmail());
        cmd.add((p.getAddress().isPrivate() ? "pa/" : "a/") + p.getAddress());

        Set<Tag> tags = p.getTags();
        for(Tag t: tags){
            cmd.add("t/" + t.tagName);
        }

        return cmd.toString();
    }

    /** Generates the correct add command based on the person given */
    String generateAddEmpCommand(Employee e) {
        StringJoiner cmd = new StringJoiner(" ");

        cmd.add("addemp");

        cmd.add(e.getName().toString());
        cmd.add("p/" + e.getPhone().toString());
        cmd.add("e/" + e.getEmail().toString());
        cmd.add("a/" + e.getAddress().toString());
        cmd.add("pos/" + e.getPosition().toString());

        return cmd.toString();
    }

    /** Generates the correct add member command based on the member given */
    String generateAddMemberCommand(Member e) {
        StringJoiner cmd = new StringJoiner(" ");

        cmd.add("addmember");

        cmd.add(e.getName().toString());

        return cmd.toString();
    }

    /** Generates the correct add menu command based on the menu item given */
    String generateMenuAddCommand(Menu m) {
        StringJoiner cmd = new StringJoiner(" ");

        cmd.add("addmenu");

        cmd.add(m.getName().toString());
        cmd.add(("p/") + m.getPrice());
        cmd.add(("type/") + m.getType());

        Set<Tag> tags = m.getTags();
        for(Tag t: tags){
            cmd.add("t/" + t.tagName);
        }

        return cmd.toString();
    }


    /**
     * Generates an Rms with auto-generated persons.
     * @param isPrivateStatuses flags to indicate if all contact details of respective persons should be set to
     *                          private.
     */
    Rms generateRms(Boolean... isPrivateStatuses) throws Exception{
        Rms rms = new Rms();
        addToRms(rms, isPrivateStatuses);
        return rms;
    }

    /**
     * Generates an Rms based on the list of Persons given.
     */
    Rms generateRms(List<Person> persons) throws Exception{
        Rms rms = new Rms();
        addToRms(rms, persons);
        return rms;
    }

    /**
     * Generates an Rms based on the list of Employees given.
     */
    Rms generateRmsEmployees(List<Employee> employees) throws Exception{
        Rms rms = new Rms();
        addEmployeesToRms(rms, employees);
        return rms;
    }

    /**
     * Generates an Rms based on the list of Menu given.
     */
    Rms generateRmsMenu(List<Menu> menus) throws Exception{
        Rms rms = new Rms();
        addToRmsMenu(rms, menus);
        return rms;
    }

    /**
     * Generates an Rms based on the list of Member given.
     */
    Rms generateRmsMember(List<Member> members) throws Exception{
        Rms rms = new Rms();
        addMembersToRms(rms, members);
        return rms;
    }

    /**
     * Adds auto-generated Person objects to the given Rms
     * @param rms The Rms to which the Persons will be added
     * @param isPrivateStatuses flags to indicate if all contact details of generated persons should be set to
     *                          private.
     */
    void addToRms(Rms rms, Boolean... isPrivateStatuses) throws Exception{
        addToRms(rms, generatePersonList(isPrivateStatuses));
    }

    /**
     * Adds the given list of Persons to the given Rms
     */
    void addToRms(Rms rms, List<Person> personsToAdd) throws Exception{
        for(Person p: personsToAdd){
            rms.addPerson(p);
        }
    }

    /**
     * Adds auto-generated Menu objects to the given Rms
     * @param rms The Rms to which the Menus will be added
     * @param isPrivateStatuses flags to indicate if details of generated persons should be set to
     *                          private.
     */
         /*void addToRMS(Rms rms, Boolean... isPrivateStatuses) throws Exception{
             addToRMS(rms, generatePersonList(isPrivateStatuses));
         }*/

    /**
     * Adds the given list of Menus to the given Rms
     */
    void addToRmsMenu(Rms rms, List<Menu> menusToAdd) throws Exception{
        for(Menu m: menusToAdd){
            rms.addMenu(m);
        }
    }

    /**
     * Adds the given list of Persons to the given Rms
     */
    void addEmployeesToRms(Rms rms, List<Employee> employeesToAdd) throws Exception{
        for(Employee e: employeesToAdd){
            rms.addEmployee(e);
        }
    }

    /**
     * Adds the given list of Members to the given Rms
     */
    void addMembersToRms(Rms rms, List<Member> membersToAdd) throws Exception{
        for(Member member: membersToAdd){
            rms.addMember(member);
        }
    }

    /**
     * Creates a list of Persons based on the give Person objects.
     */
    List<Person> generatePersonList(Person... persons) throws Exception{
        List<Person> personList = new ArrayList<>();
        for(Person p: persons){
            personList.add(p);
        }
        return personList;
    }

    /**
     * Creates a list of Employees based on the give Employee objects.
     */
    List<Employee> generateEmployeeList(Employee... employees) throws Exception{
        List<Employee> employeeList = new ArrayList<>();
        for(Employee e: employees){
            employeeList.add(e);
        }
        return employeeList;
    }

    /**
     * Creates a list of Members based on the give Member objects.
     */
    List<Member> generateMemberList(Member... members) throws Exception{
        List<Member> memberList = new ArrayList<>();
        for(Member member: members){
            memberList.add(member);
        }
        return memberList;
    }

    /**
     * Creates a list of Menu Items based on the give Menu objects.
     */
    List<Menu> generateMenuList(Menu... menus) throws Exception{
        List<Menu> menuList = new ArrayList<>();
        for(Menu m: menus){
            menuList.add(m);
        }
        return menuList;
    }

    /**
     * Generates a list of Persons based on the flags.
     * @param isPrivateStatuses flags to indicate if all contact details of respective persons should be set to
     *                          private.
     */
    List<Person> generatePersonList(Boolean... isPrivateStatuses) throws Exception{
        List<Person> persons = new ArrayList<>();
        int i = 1;
        for(Boolean p: isPrivateStatuses){
            persons.add(generatePerson(i++, p));
        }
        return persons;
    }

    /**
     * Generates a Person object with given name. Other fields will have some dummy values.
     */
    Person generatePersonWithName(String name) throws Exception {
        return new Person(
                new Name(name),
                new Phone("1", false),
                new Email("1@email", false),
                new Address("House of 1", false),
                Collections.singleton(new Tag("tag"))
        );
    }

    /**
     * Generates a Member object with given name. Other fields will have some dummy values.
     */
    Member generateMemberWithName(String name) throws Exception {
        return new Member(
                new MemberName(name)
        );
    }

    /**
     * Generates a Menu object with given name. Other fields will have some dummy values.
     */
    Menu generateMenuWithName(String name) throws Exception {
        return new Menu(
                new MenuName(name),
                new Price("$5.00"),
                new Type("main"),
                Collections.singleton(new Tag("tag"))
        );
    }

    /**
     * Generates a Menu object with given name. Other fields will have some dummy values.
     */
    Menu generateMenuWithGivenNameAndType(String name, String type) throws Exception {
        return new Menu(
                new MenuName(name),
                new Price("$5.00"),
                new Type(type),
                Collections.singleton(new Tag("tag"))
        );
    }
}