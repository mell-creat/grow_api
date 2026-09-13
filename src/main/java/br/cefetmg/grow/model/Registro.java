package br.cefetmg.grow.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TbRegistro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CdRegistro")
    private Long id;

    @Column(name = "DtRegistro", nullable = false)
    private LocalDate dataRegistro;

    @Column(name = "AguaPlanta", nullable = false)
    private Integer aguaPlanta;

    @Column(name = "Luz", nullable = false)
    private Integer luz;

    @Column(name = "Nutrientes", nullable = false)
    private Integer nutrientes;

    @Column(name = "QualidCuidado", nullable = false)
    private Integer qualidadeCuidado;

    @Column(name = "XpRecebido", nullable = false)
    private Integer xpRecebido = 0;

    @Column(name = "Observacao", length = 200)
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "tb_planta_usuario_cd_planta_usuario", nullable = false)
    private PlantaUsuario plantaUsuario;
}