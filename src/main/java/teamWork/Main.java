package teamWork;

import jdbc_recap.entity.Project;
import jdbc_recap.repository.ProjectRepository;
import teamWork.entity.Guest;
import teamWork.repository.GuestRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        GuestRepository guestRepository = new GuestRepository();
        List<Guest> allGuests = guestRepository.findAll();
        System.out.println(allGuests);

        Guest newGuest = new Guest();
        newGuest.setName("Jonas");
        newGuest.setAge(35);
        newGuest.setEmailAddress("jonas@gmail.com");
        newGuest.setNationality("Lithuanian");

       // guestRepository.add(newGuest);

        System.out.println();

        System.out.println(guestRepository.findAll());

        System.out.println();
        System.out.println(guestRepository.findGuestByName("Gregor"));

        System.out.println();
        System.out.println(guestRepository.findGuestByGivenEmailEnding("gmail.com"));
    }
}
