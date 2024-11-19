package telran.employees;

import telran.io.Persistable;
import telran.net.TcpServer;

public class Main {

    private static final String FILE_NAME = "employees.data";
    private static final int PORT = 4000;

    public static void main(String[] args) {
        Company company = new CompanyImpl();
        if (company instanceof Persistable persistable) {
            persistable.restoreFromFile(FILE_NAME);
            CompanyDataManager companyDataManager = new CompanyDataManager(persistable, FILE_NAME);
            Thread thread = new Thread(companyDataManager);
            thread.start();
        }
        TcpServer tcpServer = new TcpServer(new CompanyProtocol(company), PORT);
        tcpServer.run();
    }
}
