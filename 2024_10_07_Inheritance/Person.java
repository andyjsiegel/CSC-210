import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {

    public String firstName;
    public String lastName;
    public String birthDate;

    public Person(String firstName, String lastName, String birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }
    
    public void setFirstName(String name) {
        if(!name.isEmpty() && !name.equals(null)) this.firstName = name;
        else throw new Error();
    }

    public void setLastName(String name) {
        if(!name.isEmpty() && !name.equals(null)) this.lastName = name;
        else throw new Error();
    }
    public void setBirthDate(String birthDate) {
        if (birthDate == null || birthDate.trim().isEmpty()) {
            throw new IllegalArgumentException("Birth date cannot be null or empty.");
        }
        // Validate date format (e.g., "yyyy-MM-dd")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false); // Prevents dates like "2023-02-30"
        try {
            Date date = sdf.parse(birthDate);
            // Additional check: Ensure the date is not in the future
            if (date.after(new Date())) {
                throw new IllegalArgumentException("Birth date cannot be in the future.");
            }
            this.birthDate = birthDate;
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid birth date format. Use 'yyyy-MM-dd'.");
        }
    }

    public String getFirstName(String name) {
        return this.firstName;
    }

    public String getLastName(String name) {
        return this.lastName;
    }
    public String getBirthDate(String birthDate) {
        return this.birthDate;
    }
}

