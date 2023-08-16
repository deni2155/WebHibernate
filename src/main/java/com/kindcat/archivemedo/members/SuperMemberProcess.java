package com.kindcat.archivemedo.members;

/**
 *
 * @author dreamer
 * @version 1.0.0.23 Класс для добавления, изменения и удаления участников МЭДО
 */
public class SuperMemberProcess implements SuperMemberProcessImpl {

    private final AddMemberProcess addMemberProcess;

    public SuperMemberProcess() {
        addMemberProcess = new AddMemberProcess();
    }

    /**
     * Добавление участника МЭДО
     * @param userLogin - имя пользователя
     * @param nameOrg - наименование организации
     * @param emailOrg - email организации
     * @param guidOrg - идентификатор организации
     */
    @Override
    public void addMemberProcess(String userLogin, String nameOrg, String emailOrg, String guidOrg) {
        addMemberProcess.addMember(userLogin, nameOrg, emailOrg, guidOrg);
    }
}
