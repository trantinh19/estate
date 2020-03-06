package paging;

public interface Pageable {
    Integer getPage();
    Integer getOffset();
    Integer getSize();
}
