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

clean:
	-rm -f jmod-simple.measurements
	-rm -f jmod-simple.tmp
	-rm -rf work
	-rm -f velocity.log
