package by.vonotirah.hibernate_task.datamodel;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	public abstract Long getId();

	@Override
	public int hashCode() {
		if (getId() != null) {
			return getId().hashCode();
		} else {
			return super.hashCode();
		}
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}

		if ((o == null) || !(o instanceof AbstractEntity)) {
			return false;
		}
		if (getClass() != o.getClass()) {
			return false;
		}

		final AbstractEntity other = (AbstractEntity) o;

		if (getId() == null) {
			return false;
		}

		return getId().equals(other.getId());
	}
}
