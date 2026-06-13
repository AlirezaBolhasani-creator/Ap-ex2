import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandHandler {
    private interface Command {
        void execute(String[] args);
    }
    private final Map<String, Command> commands = new HashMap<>();

    public CommandHandler() {
        commands.put("add-hotel", new AddHotel());
        commands.put("add-staff", new AddStaff());
        commands.put("edit-staff", new EditStaff());
        commands.put("remove-staff", new RemoveStaff());
        commands.put("add-guest", new AddGuest());
        commands.put("remove-guest", new RemoveGuest());
        commands.put("edit-guest", new EditGuest());
        commands.put("add-category", new AddCategory());
        commands.put("add-resource", new AddResource());
        commands.put("remove-resource", new RemoveResource());
        commands.put("edit-resource", new EditResource());
        commands.put("search-guest", new SearchGuest());
        commands.put("add-service", new AddServiceCatalog());
        commands.put("edit-service", new EditServiceCatalog());
        commands.put("remove-service", new RemoveServiceCatalog());
        commands.put("reserve", new AddReservation());
        commands.put("cancel-reserve", new CancelReservation());
        commands.put("check-in", new CheckInReservation());
        commands.put("check-out", new CheckOutReservation());
        commands.put("view-balance", new ViewBalance());
        commands.put("pay", new Pay());
        commands.put("add-comment", new Comments());
        commands.put("search", new Search());
    }
    public void start() {
        Scanner scanner = new Scanner(System.in);
        HotelSystem.setAdmin("admin", "AdminPass");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;
            if (line.equals("finish")) break;

            String[] parts = line.split("#");
            String commandName = parts[0];
            String[] args = parts.length > 1 ? parts[1].split("\\|") : new String[0];

            processCommand(commandName, args);
        }
        scanner.close();
    }
    private void processCommand(String commandName, String[] args) {
        Command cmd = commands.get(commandName);
        if (cmd != null) {
            cmd.execute(args);
        } else {
            System.out.println("invalid command");
        }
    }
    private static String editCheck(String command) {
        if(command.equals("-"))
            return null;
        return command;
    }
    private static class AddHotel implements Command {
        @Override
        public void execute(String[] args) {
            if (!HotelSystem.auth(args[0], args[1], "Admin")) return;

            Admin a = (Admin) HotelSystem.findHumanByUsername(args[0]);
            if (a != null) {
                a.addHotel(args[2], args[3], args[4],
                        Integer.parseInt(args[5]), Integer.parseInt(args[6]), args[7]);
            }
        }
    }
    private static class AddStaff implements Command {
        @Override
        public void execute(String[] args) {
            if(!HotelSystem.auth(args[0], args[1], "Admin")) {
                return;
            }
            Human h = HotelSystem.findHumanByUsername(args[0]);
            Admin a = (Admin) h;
            assert a != null;
            a.addStaff(args[2], args[3], args[4], args[5], args[6], Integer.parseInt(args[7]),
                    args[8], args[9], args[10]);
        }
    }
    private static class EditStaff implements Command {
        @Override
        public void execute(String[] args) {
            if(!HotelSystem.auth(args[0], args[1], "Admin")) {
                return;
            }
            Human h = HotelSystem.findHumanByUsername(args[0]);
            Admin a = (Admin) h;
            assert a != null;
            a.editStaff(args[2], editCheck(args[3]), editCheck(args[4]), editCheck(args[5]), editCheck(args[6]),
            args[7].equals("-")? null : Integer.parseInt(args[7]),editCheck(args[8]), editCheck(args[9]),editCheck(args[10]));
        }
    }
    private static class RemoveStaff implements Command {
        @Override
        public void execute(String[] args) {
            if(!HotelSystem.auth(args[0], args[1], "Admin")) {
                return;
            }
            Human h = HotelSystem.findHumanByUsername(args[0]);
            Admin a = (Admin) h;
            assert a != null;
            a.removeStaff(args[2]);
        }
    }
    private static class AddGuest implements Command {
        @Override
        public void execute(String[] args) {
            if(!HotelSystem.auth(args[0], args[1], "Admin")) {
                return;
            }
            Human h = HotelSystem.findHumanByUsername(args[0]);
            Admin a = (Admin) h;
            assert a != null;
            a.addGuest(args[2], args[3], args[4], args[5], args[6], args[7], args[8]);
        }
    }
    private static class RemoveGuest implements Command {
        @Override
        public void execute(String[] args) {
            if(!HotelSystem.auth(args[0], args[1], "Admin")) {
                return;
            }
            Human h = HotelSystem.findHumanByUsername(args[0]);
            Admin a = (Admin) h;
            assert a != null;
            a.removeGuest(args[2]);
        }
    }
    private static class EditGuest implements Command {
        @Override
        public void execute(String[] args) {
            if(!HotelSystem.auth(args[0], args[1], "Admin")) {
                return;
            }
            Human h = HotelSystem.findHumanByUsername(args[0]);
            Admin a = (Admin) h;
            assert a != null;
            a.editGuest(args[2], editCheck(args[3]), editCheck(args[4]), editCheck(args[5]),
                    editCheck(args[6]), editCheck(args[7]), editCheck(args[8]));
        }
    }
    private static class AddCategory implements Command {
        @Override
        public void execute(String[] args) {
            if(!HotelSystem.auth(args[0], args[1], "Admin")) {
                return;
            }
            Human h = HotelSystem.findHumanByUsername(args[0]);
            Admin a = (Admin) h;
            assert a != null;
            if(args[4].equals("null")) {
                args[4] = null;
            }
            a.addCategory(args[2], args[3], args[4]);
        }
    }
    private static class AddResource implements Command {
        @Override
        public void execute(String[] args) {
            if(!HotelSystem.auth(args[0], args[1], "manager")) {
                return;
            }
            Human h = HotelSystem.findHumanByUsername(args[0]);
            Manager m = (Manager) h;
            assert m != null;
            m.addResource(args[2], args[3], Integer.parseInt(args[4]), Long.parseLong(args[5]),
                    args[6], args[7], args[8]);
        }
    }
    private static class EditResource implements Command {
        @Override
        public void execute(String[] args) {
            if(!HotelSystem.auth(args[0], args[1], "manager")) {
                return;
            }
            Human h = HotelSystem.findHumanByUsername(args[0]);
            Manager m = (Manager) h;
            assert m != null;
            m.editResource(args[2], editCheck(args[3]), editCheck(args[4]) == null ? null : Integer.parseInt(args[4]),
                    editCheck(args[5]) == null? null: Long.parseLong(args[5]), editCheck(args[6]), editCheck(args[7]),
                    editCheck(args[8]));
        }
    }
    private static class RemoveResource implements Command {
        @Override
        public void execute(String[] args) {
            if(!HotelSystem.auth(args[0], args[1], "manager")) {
                return;
            }
            Human h = HotelSystem.findHumanByUsername(args[0]);
            Manager m = (Manager) h;
            assert m != null;
            m.removeResource(args[2], args[3]);
        }
    }
    private static class SearchGuest implements Command {
        @Override
        public void execute(String[] args) {
            if(!HotelSystem.auth(args[0], args[1], "Staff")) {
                return;
            }
            Human h = HotelSystem.findHumanByUsername(args[0]);
            Staff s = (Staff) h;
            assert s != null;
            s.searchGuest(args[2]);

        }
    }
    private static class AddServiceCatalog implements Command {
        @Override
        public void execute(String[] args) {
            if(!HotelSystem.auth(args[0], args[1], "manager")) {
                return;
            }
            Human h = HotelSystem.findHumanByUsername(args[0]);
            Manager m = (Manager) h;
            assert m != null;
            m.addServiceCatalog(args[2], args[3], ServiceCatalog.getServiceTypeByString(args[4]),
                    Integer.parseInt(args[5]), LocalTime.parse(args[6]), LocalTime.parse(args[7]), args[8]);
        }
    }
    private static class EditServiceCatalog implements Command {
        @Override
        public void execute(String[] args) {
            if(!HotelSystem.auth(args[0], args[1], "manager")) {
                return;
            }
            Human h = HotelSystem.findHumanByUsername(args[0]);
            Manager m = (Manager) h;
            assert m != null;
            ServiceCatalog.ServiceType type = args[4].equals("-")? null: ServiceCatalog.getServiceTypeByString(args[4]);
            Integer price = args[5].equals("-")? null: Integer.parseInt(args[5]);
            LocalTime start = args[6].equals("-")? null: LocalTime.parse(args[6]);
            LocalTime end = args[7].equals("-")? null: LocalTime.parse(args[7]);

            m.editServiceCatalog(args[2], editCheck(args[3]), type, price, start, end, editCheck(args[8]));
        }
    }
    private static class RemoveServiceCatalog implements Command {
        @Override
        public void execute(String[] args) {
            if(!HotelSystem.auth(args[0], args[1], "manager")) {
                return;
            }
            Human h = HotelSystem.findHumanByUsername(args[0]);
            Manager m = (Manager) h;
            assert m != null;
            m.removeServiceCatalog(args[2], args[3]);
        }
    }
    private static class AddReservation implements Command {
        @Override
        public void execute(String[] args) {
            if(!HotelSystem.auth(args[0], args[1], "Guest")) {
                return;
            }
            Human h = HotelSystem.findHumanByUsername(args[0]);
            Guest g = (Guest) h;
            assert g != null;
            g.addReservation(args[2], args[3], LocalDate.parse(args[4]), LocalDate.parse(args[5]),
                    LocalDate.parse(args[6]), LocalTime.parse(args[7]));
        }
    }
    private static class CancelReservation implements Command {
        @Override
        public void execute(String[] args) {
            if(!HotelSystem.auth(args[0], args[1], "Guest")) {
                return;
            }
            Human h = HotelSystem.findHumanByUsername(args[0]);
            Guest g = (Guest) h;
            assert g != null;
            g.cancelReservation(Integer.parseInt(args[2]), LocalDate.parse(args[3]), LocalTime.parse(args[4]));
        }
    }
    private static class CheckInReservation implements Command {
        @Override
        public void execute(String[] args) {
            if(!HotelSystem.auth(args[0], args[1], "Staff")) {
                return;
            }
            Human h = HotelSystem.findHumanByUsername(args[0]);
            Staff s = (Staff) h;
            assert s != null;
            s.checkIn(args[2], args[3], Integer.parseInt(args[4]), LocalDate.parse(args[5]), LocalTime.parse(args[6]));
        }
    }
    private static class CheckOutReservation implements Command {
        @Override
        public void execute(String[] args) {
            if(!HotelSystem.auth(args[0], args[1], "Staff")) {
                return;
            }
            Human h = HotelSystem.findHumanByUsername(args[0]);
            Staff s = (Staff) h;
            assert s != null;
            s.checkOut(args[2], args[3], Integer.parseInt(args[4]), LocalDate.parse(args[5]), LocalTime.parse(args[6]));
        }
    }
    private static class ViewBalance implements Command {
        @Override
        public void execute(String[] args) {
            if(!HotelSystem.auth(args[0], args[1], "Guest")) {
                return;
            }
            Human h = HotelSystem.findHumanByUsername(args[0]);
            Guest g = (Guest) h;
            assert g != null;
            g.viewBalance();
        }
    }
    private static class Pay implements Command {
        @Override
        public void execute(String[] args) {
            if(!HotelSystem.auth(args[0], args[1], "Guest")) {
                return;
            }
            Human h = HotelSystem.findHumanByUsername(args[0]);
            Guest g = (Guest) h;
            assert g != null;
            g.pay(Integer.parseInt(args[2]),  LocalDate.parse(args[3]), LocalTime.parse(args[4]));
        }
    }
    private static class Comments implements Command {
        @Override
        public void execute(String[] args) {
            if(!HotelSystem.auth(args[0], args[1], "Guest")) {
                return;
            }
            Human h = HotelSystem.findHumanByUsername(args[0]);
            Guest g = (Guest) h;
            assert g != null;
            g.addCommentForResource(args[2], args[3], args[4]);
        }
    }
    private static class Search implements Command {
        @Override
        public void execute(String[] args) {
            Human.search(args[0]);
        }
    }
}
