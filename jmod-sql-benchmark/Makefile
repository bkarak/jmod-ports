JMOD_COMPILER=java -jar jmodv2.jar
JAVA_COMPILER=javac

benchmark:
	@echo 'Calculating compiler overhead (jmod-simple)'
	@-rm -f jmod-simple.measurements
	@count=1000 ; \
	while [ $$count -gt 0 ] ; \
	do \
		{ time $(JMOD_COMPILER) -i jmod-simple ; } 2> jmod-simple.tmp ; \
		cat jmod-simple.tmp | grep real >> jmod-simple.measurements ; \
		rm -f jmod-simple.tmp ; \
		count=`expr $$count - 1` ; \
		rm -rf work ; \
	done
	@echo 'Calculating compiler overhead (jmod-live-database)'
	@-rm -f jmod-live-database.measurements
	@count=1000 ; \
	while [ $$count -gt 0 ] ; \
	do \
		{ time $(JMOD_COMPILER) -i jmod-live-database ; } 2> jmod-live-database.tmp ; \
		cat jmod-live-database.tmp | grep real >> jmod-live-database.measurements ; \
		rm -f jmod-live-database.tmp ; \
		count=`expr $$count - 1` ; \
		rm -rf work ; \
	done
	@echo 'Calculating compiler overhead (jmod-ns-aware)'
	@-rm -f jmod-ns-aware.measurements
	@count=1000 ; \
	while [ $$count -gt 0 ] ; \
	do \
		{ time $(JMOD_COMPILER) -i jmod-ns-aware ; } 2> jmod-ns-aware.tmp ; \
		cat jmod-ns-aware.tmp | grep real >> jmod-ns-aware.measurements ; \
		rm -f jmod-ns-aware.tmp ; \
		count=`expr $$count - 1` ; \
		rm -rf work ; \
	done


clean:
	-rm -f jmod-simple.measurements
	-rm -f jmod-simple.tmp
	-rm -rf work
	-rm -f velocity.log
