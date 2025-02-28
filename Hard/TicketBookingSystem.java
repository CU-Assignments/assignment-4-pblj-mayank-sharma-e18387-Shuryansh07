import java.util.*;

class TicketBookingSystem {
    private final int totalSeats;
    private final boolean[] seats; // To track booked seats

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = totalSeats;
        this.seats = new boolean[totalSeats]; // false means available
    }

    // Synchronized method to prevent double booking
    public synchronized boolean bookSeat(int seatNumber, String customerType) {
        if (seatNumber < 0 || seatNumber >= totalSeats) {
            System.out.println(customerType + ": Invalid seat number " + seatNumber);
            return false;
        }

        if (!seats[seatNumber]) {
            seats[seatNumber] = true;
            System.out.println(customerType + " booked seat " + seatNumber);
            return true;
        } else {
            System.out.println(customerType + " tried to book seat " + seatNumber + " but it was already booked.");
            return false;
        }
    }
}

// Booking Thread
class BookingThread extends Thread {
    private final TicketBookingSystem system;
    private final int seatNumber;
    private final String customerType;

    public BookingThread(TicketBookingSystem system, int seatNumber, String customerType, int priority) {
        this.system = system;
        this.seatNumber = seatNumber;
        this.customerType = customerType;
        setPriority(priority); // Set thread priority (higher for VIP)
    }

    @Override
    public void run() {
        system.bookSeat(seatNumber, customerType);
    }
}

// Main class to test multithreading
class TicketBookingDemo {
    public static void main(String[] args) {
        int totalSeats = 5;
        TicketBookingSystem system = new TicketBookingSystem(totalSeats);

        // Creating threads for booking
        BookingThread t1 = new BookingThread(system, 2, "VIP", Thread.MAX_PRIORITY);
        BookingThread t2 = new BookingThread(system, 2, "Regular", Thread.MIN_PRIORITY);
        BookingThread t3 = new BookingThread(system, 3, "VIP", Thread.MAX_PRIORITY);
        BookingThread t4 = new BookingThread(system, 3, "Regular", Thread.MIN_PRIORITY);
        BookingThread t5 = new BookingThread(system, 1, "Regular", Thread.NORM_PRIORITY);

        // Starting threads
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
