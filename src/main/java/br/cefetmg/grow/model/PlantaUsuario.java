package br.cefetmg.grow.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TbPlantaUsuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlantaUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CdPlantaUsuario")
    private Long id;

    @Column(name = "Apelido", length = 45, nullable = false)
    private String apelido;

    @Column(name = "XpAtual", nullable = false)
    private Integer xpAtual = 0;

    @Column(name = "Nivel", nullable = false)
    private Integer nivel = 1;

    @Column(name = "Felicidade", nullable = false)
    private Integer felicidade = 100;

    @Column(name = "Saude", nullable = false)
    private Integer saude = 100;

    @Column(name = "Publica", nullable = false)
    private Boolean publica = false;

    @ManyToOne
    @JoinColumn(name = "Fase", nullable = false)
    private FasePlanta fase;

    @Column(name = "DtCriacao", nullable = false)
    private LocalDate dataCriacao;

    @Column(name = "UltimaAlteracao", nullable = false)
    private LocalDate ultimaAlteracao;

    @ManyToOne
    @JoinColumn(name = "tbUsuario_CdUsuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "TbEspeciePlanta_CdEspecie", nullable = false)
    private EspeciePlanta especiePlanta;

    // Adicione esta linha para o Hibernate saber que existem registros filhos
    // vinculados
    @OneToMany(mappedBy = "plantaUsuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Registro> registros = new ArrayList<>();
}