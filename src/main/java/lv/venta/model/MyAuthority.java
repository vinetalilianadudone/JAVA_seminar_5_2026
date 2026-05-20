package lv.venta.model;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table
@Entity
public class MyAuthority {

	@Setter(value = AccessLevel.NONE)
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ida;
	
	@Column
	@NotNull
	@NotEmpty
	@Pattern(regexp = "[A-Za-z0-9., ]{3,50}")
	private String title;
	
}
