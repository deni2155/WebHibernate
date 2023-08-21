package com.kindcat.archivemedo.members;

/**
 *
 * @author dreamer
 * @version 1.0.0.23 Класс для добавления, изменения и удаления участников МЭДО
 */
public class SuperMemberProcess implements SuperMemberProcessImpl {

    private final VerifMemberForAddProcess addMemberProcess;

    public SuperMemberProcess(String userLogin, String nameOrg, String emailOrg, String guidOrg) {
        addMemberProcess = new VerifMemberForAddProcess(userLogin, nameOrg, emailOrg, guidOrg);
    }

    /**
     * Добавление участника МЭДО
     */
    @Override
    public void addMemberProcess() {
        addMemberProcess.addMember();
    }

    /**
     * Проверяю на соответствие добавляемых данных регулярным выражениям
     *
     * @return true, если все поля заполнены верно
     */
    @Override
    public boolean verifProcessRegex() {
        return addMemberProcess.verifRegex();
    }
}
