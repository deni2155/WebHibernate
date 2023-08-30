package com.kindcat.archivemedo.pagination;

/**
 *
 * @author dreamer
 * @vrsion 1.0.1.33 Класс для настройки пагинации
 */
public class SuperPagination {
    
    //ссылка на класс для настройки пагинации участников МЭДО
    private final MembersPagination memberspagination;

    public SuperPagination() {
        memberspagination = new MembersPagination();
    }
}
