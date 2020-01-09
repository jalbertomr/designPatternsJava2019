package com.bext.structural.adapter.interfaceAdapterService;

import java.util.ArrayList;
import java.util.List;

public class InterfaceAdapterServiceDemo {
    public static void main(String... args) {
        String titular;
        IAdapter iadapter = new ServiceEmailAdapter();
        titular = iadapter.getPersonName();
        System.out.println(titular);
        iadapter = new ServiceGithubAdapter();
        titular = iadapter.getPersonName();
        System.out.println(titular);
        iadapter = new ServiceNetFlixAdapter();
        titular = iadapter.getPersonName();
        System.out.println(titular);
        // Or
        System.out.println( new ServiceEmailAdapter().getPersonName());
        System.out.println( new ServiceGithubAdapter().getPersonName());
        System.out.println( new ServiceNetFlixAdapter().getPersonName());
        // or
        List<IAdapter> listAdapters = new ArrayList<>();
        listAdapters.add( new ServiceEmailAdapter());
        listAdapters.add( new ServiceGithubAdapter());
        listAdapters.add( new ServiceNetFlixAdapter());
        for ( IAdapter adapter : listAdapters) {
            System.out.println( adapter.getPersonName());
        }
    }

    // Interface to Adapter
    interface IAdapter{
        public String getPersonName();
    }
    // Adapters
    static class ServiceEmailAdapter implements IAdapter {
        ServiceEmail serviceEmail = new ServiceEmail();

        public String getPersonName() {
            return serviceEmail.getFirstName() + " " + serviceEmail.getLastName();
        }
    }

    static class ServiceGithubAdapter implements IAdapter {
        ServiceGithub serviceGithub = new ServiceGithub();

        public String getPersonName() {
            return serviceGithub.getName();
        }
    }

    static class ServiceNetFlixAdapter implements IAdapter {
        ServiceNetFlix serviceNetFlix = new ServiceNetFlix();

        public String getPersonName() {
            return serviceNetFlix.getPerfilName();
        }
    }
    // Services
    static class ServiceEmail {
        private String email;
        private String nickName;
        private String firstName = "Jose Alberto";
        private String lastName = "Martinez";
        // ....
        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }
    }

    static class ServiceGithub {
        private String githutId;
        private String name = "Jose Alberto Martinez";

        public String getName() {
            return name;
        }
    }

    static class ServiceNetFlix {
        private String email;
        private String perfilName = "Beto Martinez";

        public String getPerfilName() {
            return perfilName;
        }
    }


}
