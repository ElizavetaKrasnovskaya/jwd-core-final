package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;

/**
 * Should be a builder for {@link BaseEntity} fields
 */
public abstract class Criteria<T extends BaseEntity> {

    private String name;
    private Long id;

    protected Criteria(Criteria.Builder builder){
        name = builder.name;
        id = builder.id;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    static class Builder <T extends Builder<T>>{
        public String name;
        private Long id;

        public Builder(){}

        public T name(String name){
            this.name=name;
            return (T) this;
        }

        public T id(Long id){
            this.id=id;
            return (T) this;
        }
    }

}
