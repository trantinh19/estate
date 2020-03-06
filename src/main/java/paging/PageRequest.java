package paging;

public class PageRequest implements Pageable {
    private Integer page;
    private Integer size;

    public PageRequest() {
    }

    public PageRequest(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }

    @Override
    public Integer getPage() {
        return this.page;
    }

    @Override
    public Integer getOffset() {
        return (this.page -1 ) * this.size;
    }

    @Override
    public Integer getSize() {
        return this.size;
    }
}
