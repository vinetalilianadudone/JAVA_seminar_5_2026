package lv.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "MyUserTable")
@Entity
public class MyUser {
	
	@Column(name = "Username")
	@NotNull
	@NotEmpty
	@Pattern(regexp = "[A-Za-z0-9_]{3,20}")
	@Size(min = 3, max = 20)
	private String username;
	
	@Setter(value = AccessLevel.NONE)
	@Column(name = "Idu")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idu;
	
	@Column(name = "Password")
	@NotNull
	@NotEmpty
	@Size(min = 6, max = 12)
	private String password;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Ida")
	private MyAuthority myAuthority;

	public MyUser(String inputUsername, String inputPassword, MyAuthority inputMyAuthority) {
		setUsername(inputUsername);
		setPassword(inputPassword);
		setMyAuthority(inputMyAuthority);
	}
}
