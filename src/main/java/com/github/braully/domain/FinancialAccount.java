//
// This file was generated by the JPA Modeler
//
package com.github.braully.domain;

import com.github.braully.util.UtilValidation;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(schema = "financial")
@DiscriminatorValue("0")
@DiscriminatorColumn(discriminatorType = DiscriminatorType.INTEGER, name = "type_id",
        columnDefinition = "smallint default '0'", length = 1)
public class FinancialAccount
        extends AbstractMigrableEntity
        implements Serializable, IOrganiztionEntityDependent {

    @ManyToOne
    protected Organization organization;

    @ManyToOne
    protected Bank bank;

    @Basic
    protected String number;

    @Basic
    protected String agency;

    @Basic
    protected String name;

    @Basic
    protected String description;

    @Basic
    protected String type;

    @Basic
    protected String codOfx;

    @ManyToOne(targetEntity = Account.class)
    protected FinancialAccount parentAccount;

    public FinancialAccount() {

    }

    @Override
    public String preToString() {
        StringBuilder sb = new StringBuilder();
        if (UtilValidation.isStringValid(description)) {
            sb.append(description).append(" ");
        }
        if (UtilValidation.isStringValid(type)) {
            sb.append(type).append(": ");
        }
        sb.append(name);

        return sb.toString();
    }

    @Override
    public String posToString() {
        StringBuilder sb = new StringBuilder();
        if (bank != null) {
            sb.append(" ").append(bank.getName());
        }

        if (UtilValidation.isStringValid(agency, number)) {
            sb.append(" ag:").append(agency).append(" ct:").append(number);
        }

        return sb.toString();
    }

    //Temporario
    public String getAgencia() {
        return agency;
    }

    public String getAgenciaSemDv() {
        return "" + this.getAgenciaSomenteNumero().substring(0, getAgenciaSomenteNumero().length() - 1);
    }

    public String getAgenciaDv() {
        return "" + this.getAgenciaSomenteNumero().charAt(getAgenciaSomenteNumero().length() - 1);
    }

    public String getAgenciaSomenteNumero() {
        return this.agency.replaceAll("\\D", "");
    }

    public String getContaSomenteNumeros() {
        return this.number.replaceAll("\\D", "");
    }

    public String getConta() {
        return this.number;
    }

    public String getContaDv() {
        return "" + this.number.charAt(number.length() - 1);
    }

    public String getCodigoAgenciaDv() {
        return agency;
    }

    public String getContaFormatada() {
        return number;
    }

    public String getAgenciaFormatada() {
        return agency;
    }

    public String getModalidade() {
        return "";
    }

    //For legacy
    public void setConta(String numConta) {
        this.number = numConta;
    }

    public void setBanco(Bank banco) {
        this.bank = banco;
    }

    public void setCodigoOfx(String accountNumber) {
        this.codOfx = accountNumber;
    }

    public void setNome(String nome) {
        this.name = nome;
    }
}
