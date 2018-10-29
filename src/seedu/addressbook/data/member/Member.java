package seedu.addressbook.data.member;


import seedu.addressbook.data.exception.IllegalValueException;

import java.awt.*;
import java.util.Date;
import java.util.Objects;

public class Member implements ReadOnlyMember {
    private MemberName name;
    private Points points;
    private Date date;

    public final String EMPTY_NAME_STRING = "EMPTY";

    public Member() {
        try {
            this.name = new MemberName(EMPTY_NAME_STRING);
        } catch (IllegalValueException ie) {
            this.name = null;
        }
        this.points = new Points();
        this.date = new Date();
    }

    public Member(MemberName name) {
        this.name = name;
        this.points = new Points();
        this.date = new Date();
    }

    public Member(MemberName name, Points points, Date date) {
        this.name = name;
        this.points = points;
        this.date = date;
    }

    /**
     * Copy constructor.
     */
//    public Member(ReadOnlyMember source) {
//        this(source.getName(), source.getPoints());
//    }

    public Member(ReadOnlyMember source) {
        this(source.getName(), source.getPoints(), source.getDate());
    }

    @Override
    public MemberName getName() {
        return name;
    }

    @Override
    public Points getPoints() { return points; }

    public Points updatePoints(double price) {
        return this.points.updatePoints(price);
    }

    public Date getDate() {
        return date;
    }
    protected void setName(MemberName name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyMember // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyMember) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return getAsTextShowAll();
    }
}