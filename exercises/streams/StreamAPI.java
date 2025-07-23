package exercises.streams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class StreamAPI {
    private List<TestUser> users;
    public void run() {
        regenUsers();
//        checkDistinct();
        // https://binkurt.blogspot.com/2017/10/exercises-to-study-java-stream-api.html
        groupAndCountRoles();
    }

    private void groupAndCountRoles() {
        Map<String, Long> rolesCount = new HashMap<>();
        rolesCount = users.stream()
                .flatMap(user -> user.getRoles().stream())
                .collect(Collectors.groupingBy(String::valueOf, Collectors.counting()));
        System.out.println("groupAndCountRoles result: " + rolesCount.toString());
    }


    private void checkDistinct() {
        final List<TestUser> unique = new ArrayList<>();
        users.forEach(user -> {
            boolean already = unique.stream().anyMatch(uniqueUser -> uniqueUser.getUsername().equals(user.getUsername()));
            if (already) return;
            unique.add(user);
        });
        System.out.println("checkDistinct - start");
        unique.forEach(this::consoleLog);
        System.out.println("checkDistinct - end");
    }

    private void regenUsers() {
        System.out.println("Regen users - start");
        List<TestUser> newUsers = new ArrayList();
        newUsers.add(new TestUser("user1", List.of("admin", "user"), 5000));
        newUsers.add(new TestUser("user2", List.of("user"), 2000));
        newUsers.add(new TestUser("user2", List.of("user"), 2000));
        users = newUsers;
        users.forEach(this::consoleLog);
        System.out.println("Regen users - end");
    }

    private void consoleLog(TestUser user) {
        System.out.println(String.format(
                        "Username: %s, roles: %s, salary: %d",
                        user.getUsername(),
                        String.join(", ", user.getRoles()),
                        user.getSalary()
                )
        );
    }

    private static class TestUser {
        private String username;
        private List<String> roles;
        private long salary;

        public TestUser(String username, List<String> roles, long salary) {
            this.username = username;
            this.roles = roles;
            this.salary = salary;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TestUser testUser = (TestUser) o;
            return salary == testUser.salary && Objects.equals(username, testUser.username) && Objects.equals(roles, testUser.roles);
        }

        @Override
        public int hashCode() {
            return Objects.hash(username, roles, salary);
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public List<String> getRoles() {
            return roles;
        }

        public void setRoles(List<String> roles) {
            this.roles = roles;
        }

        public long getSalary() {
            return salary;
        }

        public void setSalary(long salary) {
            this.salary = salary;
        }
    }
}
