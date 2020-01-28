package br.com.eiasiscon.security.user.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.eiasiscon.base.BaseEntity;

@Document
public class Privilege extends BaseEntity {

	private static final long serialVersionUID = 7392606791763398931L;
	
	private String name;
	
	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
	
	@Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Privilege other = (Privilege) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Privilege [name=").append(name).append("]").append("[id=").append(getId()).append("]");
        return builder.toString();
    }

}
