-- Dialectosql de spark para carga/descargar datos
-- queries emplean ansi-sql

create temporary table vuelos1987
	using csv
	options (
		path "./data/1987.csv",
		header "true"
	); 
	
insert overwrite directory 
	using csv
	options (
		path "/tmp/informes"
	)	
select 
  Origin, SUM(CAST(Distance AS INTEGER)) as Total 
from  VUELOS1987
group by Origin
order by Origin;