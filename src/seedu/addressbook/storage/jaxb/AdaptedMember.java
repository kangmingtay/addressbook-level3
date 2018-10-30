package seedu.addressbook.storage.jaxb;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;

import seedu.addressbook.common.Utils;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.member.*;


/**
 * JAXB-friendly adapted person data holder class.
 */
public class AdaptedMember {

    /**
     * JAXB-friendly adapted contact detail data holder class.
     */
    private static class AdaptedContactDetail {
        private String value;
        private boolean isPrivate;

        @XmlValue
        public String getValue() {
            return value;
        }

        @XmlAttribute(name = "isPrivate", required = true)
        public boolean isPrivate() {
            return isPrivate;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public void setPrivate(boolean aPrivate) {
            isPrivate = aPrivate;
        }
    }

    @XmlElement(required = true)
    private String name;

    @XmlElement(required = true)
    private String points;

    @XmlElement(required = true)
    private long date;

    @XmlElement(required = true)
    private String tier;

    /**
     * No-arg constructor for JAXB use.
     */
    public AdaptedMember() {}


    /**
     * Converts a given Person into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created AdaptedPerson
     */
    public AdaptedMember(ReadOnlyMember source) {
        name = source.getName().fullName;
        points = source.getPoints().toString();
        date = source.getDate().getTime();
        tier = source.getMemberTier().toString();
    }

    /**
     * Returns true if any required field is missing.
     *
     * JAXB does not enforce (required = true) without a given XML schema.
     * Since we do most of our validation using the data class constructors, the only extra logic we need
     * is to ensure that every xml element in the document is present. JAXB sets missing elements as null,
     * so we check for that.
     */
    public boolean isAnyRequiredFieldMissing() {
        /*
        for (AdaptedTag tag : tagged) {
            if (tag.isAnyRequiredFieldMissing()) {
                return true;
            }
        }
        */
        // second call only happens if phone/email/address are all not null
        return Utils.isAnyNull(name);
        // || Utils.isAnyNull(phone.value, email.value, address.value);
    }

    /**
     * Converts this jaxb-friendly adapted person object into the Person object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person
     */
    public Member toModelType() throws IllegalValueException {
        /*
        final Set<Tag> tags = new HashSet<>();
        for (AdaptedTag tag : tagged) {
            tags.add(tag.toModelType());
        }
        */
        final MemberName name = new MemberName(this.name);
        final Points points = new Points(Integer.parseInt(this.points));
        final Date date = new Date(this.date);
        final MemberTier tier = new MemberTier(this.tier);

        return new Member(name, points, date, tier);
    }
}
