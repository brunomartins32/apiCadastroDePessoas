package com.example.cadastro.api.dto;


import com.example.cadastro.api.model.Pessoa;

//DTO significa 'Data Transfer Object' ou ' Object de transferência de dados'
// esse cara é responsável por passar um dado vindo de fora da API para dentro do banco de dados
public class PessoaDTO {

    private Long codigo;
    private String nome;
    private Integer idade;
    private char sexo;


    public PessoaDTO(){

    }

    public PessoaDTO(Long codigo, String nome, Integer idade, char sexo) {
        this.codigo = codigo;
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
    }

    //Vamos usar esse método para transformar uma objeto do tipo Pessoa para um DTO
    public static PessoaDTO toDTO(Pessoa model){
        PessoaDTO p = new PessoaDTO();
        p.setCodigo(model.getCodigo());
        p.setIdade(model.getIdade());
        p.setNome(model.getNome());
        p.setSexo(model.getSexo());

        return p;
    }

    // Vamos usar esse método para transformar um objeto do tipo PessoaDTO para um model
    public Pessoa toModel(){
        Pessoa p = new Pessoa();
        p.setCodigo(this.getCodigo());
        p.setIdade(this.getIdade());
        p.setNome(this.getNome());
        p.setSexo(this.getSexo());

        return p;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
}
