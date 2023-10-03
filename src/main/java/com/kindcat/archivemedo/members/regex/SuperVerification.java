package com.kindcat.archivemedo.members.regex;

/**
 *
 * @author dreamer
 * @version 1.0.0.23 Класс для добавления, изменения и удаления участников МЭДО
 */
public class SuperVerification implements ImplVerification {

    private final VerificationMembers addMemberProcess;

    public SuperVerification(String userLogin, String nameOrg, String emailOrg, String guidOrg) {
        addMemberProcess = new VerificationMembers(userLogin, nameOrg, emailOrg, guidOrg);
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
