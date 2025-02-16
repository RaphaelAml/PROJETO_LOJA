select * from item_venda_loja

select * from produto

select * from status_rastreio

select * from vd_cp_loja_virt

begin;
update nota_fiscal_venda set venda_compra_loja_virt_id = null where venda_compra_loja_virt_id = 17;
delete from nota_fiscal_venda where venda_compra_loja_virt_id = 17;
delete from item_venda_loja where venda_compra_loja_virt_id = 17;
delete from status_rastreio where venda_compra_loja_virt_id = 17;
delete from vd_cp_loja_virt where id = 17;
commit;

select * from item_venda_loja where venda_compra_loja_virt_id = 17

select * from vd_cp_loja_virt where id = 18;
select * from nota_fiscal_venda where venda_compra_loja_virt_id = 18;
select * from item_venda_loja where venda_compra_loja_virt_id = 18;
select * from status_rastreio where venda_compra_loja_virt_id = 18;

update vd_cp_loja_virt set excluido = false;

ALTER TABLE vd_cp_loja_virt DROP CONSTRAINT ukhkxjejv08kldx994j4serhrbu;
